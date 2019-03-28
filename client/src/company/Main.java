package company;

import company.View.Views.MainView;


public class Main {
    public static void main(String[] args) {

            try {
                MainView view = new MainView();
                view.run();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }


}
