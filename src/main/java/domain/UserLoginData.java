package domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class UserLoginData {

	private String login;
	private String password;

	public UserLoginData(){

	}

	public UserLoginData(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

    public String getPassword() {
		return password;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserLoginData that = (UserLoginData) o;

		if (login != null ? !login.equals(that.login) : that.login != null) return false;
		return password != null ? password.equals(that.password) : that.password == null;
	}

	@Override
	public int hashCode() {
		int result = login != null ? login.hashCode() : 0;
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserLoginData{");
        sb.append("login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
