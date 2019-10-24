package ejb;

import Utils.SessionUtils;
import entities.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named(value = "login")
@RequestScoped
public class login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;
    @EJB
    private LoginDAO loginDAO;

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

    public String validateUser() {
        boolean valid = loginDAO.validate(user, pwd);
        //boolean valid = brukerFinnes(this.loginDAO.getAll(), user, pwd);
        if(valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Incorrect credentials", "Please enter correct username and password"));
            return "login";
        }
    }

    public boolean brukerFinnes(List<User> users, String user, String pwd) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUsername() == user && users.get(i).getPassword() == pwd) {
                return true;
            }

        }
        return false;
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }
}
