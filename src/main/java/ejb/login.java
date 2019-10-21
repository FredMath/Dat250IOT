package ejb;

import Utils.SessionUtils;
import com.sun.deploy.net.HttpUtils;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

public class login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    /*public String validateUser() {
        boolean valid = loginDAO.validate(user, password);
        if(valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);
            return "admin";
        }
    }*/
}
