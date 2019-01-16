import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

public class MyToolWindowFactory implements ToolWindowFactory {
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

    }
    public void init(ToolWindow window) {}
    public boolean shouldBeAvailable(@NotNull Project project) { return true;}
    public boolean isDoNotActivateOnStart() {return true;}
}
