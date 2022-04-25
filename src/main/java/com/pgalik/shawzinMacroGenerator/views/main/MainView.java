package com.pgalik.shawzinMacroGenerator.views.main;


import com.pgalik.shawzinMacroGenerator.service.MacroService;
import com.pgalik.shawzinMacroGenerator.views.components.MainViewComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    private final MacroService macroService;


    public MainView(MacroService macroService) {
        this.macroService = macroService;

        MainViewComponent viewComponent = new MainViewComponent();

        viewComponent.getGenerateButton().addClickListener(click -> {
            final String macroText = getMacroText(
                    viewComponent.getShawzinStringField().getValue(),
                    viewComponent.getTitleInput().getValue()
            );
            viewComponent.setMacroText(macroText);
        });


        add(viewComponent);
    }

    private String getMacroText(final String shawzinString, final String songTitle) {
        try {
            return macroService.createMacroFromString(shawzinString, songTitle).toString();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
