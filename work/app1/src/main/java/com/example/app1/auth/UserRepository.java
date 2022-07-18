package com.example.app1.auth;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.example.app1.auth.account.ProfileEditForm;
import com.example.app1.searchUser.UserSearchForm;

@Mapper
public interface UserRepository {
    // SpringのDIコンテナがMybatisのMapperインターフェースを探すのに＠Mapperがついているものを条件に探す
    // このメソッド１つがSQLに対応している
    // Mapper.xmlはMapperインターフェースと同一のパッケージに作成する必要がある
    /** ユーザー情報の登録
     * @param user
     * @return 件数
     */
    public int registerUser(User user);
    
    /** ユーザー情報の更新
     * @param user
     * @return 件数
     */
    public int updateUser(User user);
    
    /** ユーザー権限情報の登録
     * @param user
     * @return 件数
     */
    public int registerUserRole(User user);
    
    /** ユーザー情報の重複チェック検索
     * @param email
     * @return User
     */
    public User identifyUser(String email);
    
    /** プロフィール情報の検索
     * @param id
     * @return ProfileEditForm
     */
    public ProfileEditForm createProfileEditForm(long id);
    
    /** プロフィール情報の更新
     * @param profileEditForm
     * @return 件数
     */
    public int updateProfileInfo(ProfileEditForm profileEditForm);
    
    /** ユーザー情報の一覧検索
     * @param form
     * @return User
     */
    public List<User> getUserList(UserSearchForm form);
    
    /** ユーザー情報の一覧検索結果総件数
     * @param form
     * @return 総件数
     */
    public int countUser(UserSearchForm form);
}
