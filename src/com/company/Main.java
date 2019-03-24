package com.company;
import DAO.IEmployeeDao;
import DAO.ListDao;
import com.company.Controller.MasterController;
import com.company.View.UI;


public class Main {
    public static void main(String[] args) {

            try {
                IEmployeeDao dao = new ListDao();
                MasterController controller = new MasterController(dao);
                UI ui = new UI(controller);
                ui.Run();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }


}
