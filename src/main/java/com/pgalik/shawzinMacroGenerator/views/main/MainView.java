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
            viewComponent.getResultPre().setText(getMacroText(viewComponent.getShawzinStringField().getValue()));
            viewComponent.getResultPre().setVisible(true);
        });

        add(viewComponent);
    }


    private String getMacroText(String shawzinString) {
        try {
            return macroService.createMacroFromString(shawzinString).toString();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
