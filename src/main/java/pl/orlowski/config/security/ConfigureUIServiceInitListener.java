package pl.orlowski.config.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;
import pl.orlowski.gui.LoginGui;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {


    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
        if (!LoginGui.class.equals(event.getNavigationTarget())
            && !SecurityUtils.isUserLoggedIn()) {
    event.rerouteTo(LoginGui.class);
        }
    }
}
