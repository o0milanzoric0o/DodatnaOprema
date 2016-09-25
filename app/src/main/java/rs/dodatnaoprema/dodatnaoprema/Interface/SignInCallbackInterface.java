package rs.dodatnaoprema.dodatnaoprema.Interface;

public interface SignInCallbackInterface {
    void onHaveAccClick();

    void onCreateNewAccClick();

    void onCreateAccClick(String name, String last_name, String email, String pass);

    void onLogInClick(String email, String pass);

    void onChangeUserData();

    void onForgotPassClick(String email);
}
