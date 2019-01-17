import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class MyToolWindow {
    private JPanel myToolWindowContent;

    public MyToolWindow(ToolWindow toolWindow) {
        myToolWindowContent = new JPanel();
        myToolWindowContent.setLayout(new BorderLayout());
        JButton northButton = new JButton("North");
        myToolWindowContent.add(northButton, BorderLayout.NORTH);
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}
