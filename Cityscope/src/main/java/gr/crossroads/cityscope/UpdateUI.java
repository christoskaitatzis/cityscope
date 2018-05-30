package gr.crossroads.cityscope;

/**
 * Created by Xristos on 3/4/2016.
 */
public class UpdateUI implements Runnable {
    String updateString;

    public UpdateUI(String updateString) {
        this.updateString = updateString;
    }
    public void run() {
        //txtview.setText(updateString);
    }
}
