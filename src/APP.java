import com.wanghao.ui.GameJFrame;
import com.wanghao.ui.LoginJFrame;
import com.wanghao.ui.RegisterJFrame;

public class APP {
    public static void main(String[] args) {

//        new LoginJFrame();

//        new RegisterJFrame();

        new GameJFrame();

        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);


        System.out.println("ok success!!!!push!!!");
    }
}
