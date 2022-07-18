package com.example.app1.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.example.app1.auth.DatabaseUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DatabaseUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// ユーザーの認証方式の決定する為のメソッド
		// inMemoryAuthenticationだとユーザー情報はメモリ上で管理
//		auth.inMemoryAuthentication()
		// userDetailsServiceのユーザー認証を行う
		auth.userDetailsService(userDetailsService);
			//ユーザー名、パスワード、権限
//			.withUser("user").password(passwordEncoder().encode("password")).roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		// Webアプリケーションが管理してるリソースのアクセス制御
		http.authorizeRequests()
			.antMatchers("/css/**").permitAll()
			// 新規登録画面はユーザー認証を必要としない画面なのでpermitAll()
			.antMatchers("/registration").permitAll()
			// 全てのリクエストに対して認証済みであることの確認
			// ログインしないとその後のリソースは見れない
			.anyRequest().authenticated()
			// httpBasic：認証方式（ダイヤログ表示）
			.and()
			// ユーザー認証なしなのでpermitAll()で全てのリクエストを許可する
			// ログアウトするとログイン.loginPage("/login")に指定したURLに飛ぶ
			.formLogin().loginPage("/login")
			.loginProcessingUrl("/signin")
			.failureHandler(new ForwardAuthenticationFailureHandler("/signin-error"))
//			.formLogin()
			// SpringSecurityはデフォルトでパラメーターがusernameとpasswordなのでカスタムログイン画面のパラメータに合わせる
			// 今回ならusernameの箇所がemailにしているので変更する必要がある、下でpasswordの設定をしているがなくても良い
			.usernameParameter("email")
			.passwordParameter("password").permitAll()
			.and()
			// ログアウトの処理 logoutUrl(引数)をPostメソッドでリクエストを投げると自動的にSpringSecurityがログアウト処理を実行してくれる
			// デフォルトのURLで”logout”で設定されているので下の.logoutUrl("/logout")は書かなくても良い
			.logout().logoutUrl("/logout").permitAll()
			// ログアウト時にHttpSessionを無効化してくれる（セッションを破棄してくれる）セッション上にユーザーの情報を残さない
			.invalidateHttpSession(true)
			// JSESSIONIDという名称のクッキーを削除してくれる
			.deleteCookies("JSESSIONID");
	}
	
//	@Override
//    public void configure(WebSecurity web) throws Exception {
//	    // work_file配下のファイルのアクセスを許可
//	    DefaultHttpFirewall firewall = new DefaultHttpFirewall();
//        web.httpFirewall(firewall);
//        web.ignoring().antMatchers("/work_file/**");
//    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		// ユーザー認証をする際、パスワードの平文を変換する
		// パスワードをハッシュ化してくれる
		// 商用のアプリだと独自で作成した方が安全
		// ＠Beanは依存性注入
		return new BCryptPasswordEncoder();
	}

}
