package com.pgalik.shawzinMacroGenerator.views.components;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Pre;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.StreamResource;
import lombok.Getter;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Getter
public class MainViewComponent extends VerticalLayout {
    private final TextArea shawzinStringField;
    private final TextField titleInput;
    private final Button generateButton;
    private final Anchor saveAnchor;
    private final Button saveButton;
    private final Pre resultPre;

    public MainViewComponent() {
        shawzinStringField = initShawzinStringField();
        titleInput = initTitleInput();
        generateButton = initGenerateButton();
        saveAnchor = initSaveAnchor();
        saveButton = initSaveButton();
        saveAnchor.add(saveButton);
        resultPre = initResultPre();

        add(
                new H1("Shawzin X7 Macro generator"),
                titleInput,
                shawzinStringField,
                new HorizontalLayout(
                        generateButton,
                        saveAnchor
                ),
                resultPre
        );
    }

    private TextField initTitleInput() {
        TextField textField = new TextField();
        textField.setLabel("Title of the song");
        return textField;
    }

    private TextArea initShawzinStringField() {
        TextArea inputField = new TextArea("Shawzin string");
        inputField.setWidthFull();
        inputField.setValue("5EAAKACMAESAXRAeMAhKAnNAtUBAUBGSBLRBRNBXNBnUBtUByhB4SB9MCIMCTUCYSCgUCphCvNDDUDWhDcVDtMEbEEfMElEEqSEuRE1ME4KE8MFBEFGMFLMFQUFVUFaSFeRFkMFpEFuKFzMF4UF8UGBhGHSGLMGWEGaMGfUGkSGsUGzhG4EG9MHCEHHSHMEHQUHVEHaUHfhHkhHtVHyKH8MIGEIKMIPEIUSIZRIgMIiKIlMIrEIwMI1MI6UI/hJESJJRJNMJSEJXKJcMJhUJlUJqhJvSJ0MJ+EKDMKIkKNhKTSKWUKbhKfEKkMKpRKtSKyRK5MK8KLAMLFELKMLPMLTULYULdSLiRLmMLrELwKL1ML5UL+UMDhMISMNMMWEMbMMgUMlSMsUM0lM5kM9hM+lNClNHlNMlNWNNgMNpSNuhNzUN5UN8SOAUOFhOKhOTUOYSOdROiSOmMOwhO5UO+SPDRPHSPMMPVKPeMPjSPoSPxNP2MP/SQEhQJUQSSQWUQbhQghQpkQuhQzUQ4hQ9hRGhRUhRdlRnlRslRxlR2hR7kR8SSrUSsSSuRSwMSzES4MS9ETBSTGRTNMTPKTUMTZETeMTjMTnUTsUTxST2RT6MT/EUEKUIMUNUUSUUXhUcSUhMUqEUvMU0UU5SVAUVHhVMEVRMVVEVaSVeEVjUVoEVtUVyhV3hWAVWFKWPMWYEWdMWhEWmSWrRWyMW0KW5MW9EXCMXHMXMUXQUXVSXaRXfMXkEXoKXtMXxUX3UX7hYASYFMYOEYTMYYUYdSYkUYrhYwEY1MY6RY/SZDRZKMZMKZRMZWEZbMZfMZkUZpUZuSZyRZ3MZ8EaAKaFMaJUaOUaThaYSadMamEarMawka1ha8Sa+UbClbHlbMlbRlbVlbalbjJbsMbtMb1Sb6hb/UcFUcIScMUcRhcWhcfUcjScoRctScyMc8hdFUdJSdORdTSdYMdhKdrMdvSd0Sd+NeCMeMSeQheVUeeSeiUenheshe1ke6he+UfDhfIhfRhfghfpkfyhfzlf3lf8lgAlgFlgPNgYMgiSgnhgsUgzUg1Sg6Ug+hhDhhMUhQShVRhaShfMhohhxUh2Sh7");
        return inputField;
    }

    private Button initGenerateButton() {
        Button generateButton = new Button("Generate");
        generateButton.addClickShortcut(Key.ENTER);
        return generateButton;
    }

    private Anchor initSaveAnchor() {
        Anchor downloadAnchor = new Anchor("");
        downloadAnchor.getElement().setAttribute("download", true);
        downloadAnchor.setVisible(false);
        return downloadAnchor;
    }

    private Button initSaveButton() {
        return new Button("Save", new Icon(VaadinIcon.DOWNLOAD));
    }

    private Pre initResultPre() {
        Pre pre = new Pre();
        pre.setWidth(96, Unit.VW);
        pre.setVisible(false);
        return pre;
    }

    public void setMacroText(String macroText) {
        resultPre.setText(macroText);
        resultPre.setVisible(true);
        final String songTitle = titleInput.getValue().isBlank() ? "UNKNOWN" : titleInput.getValue();
        final String fileName = "WARFRAME_" + songTitle.toUpperCase().replace(' ', '_') + ".amc";
        saveAnchor.setHref(new StreamResource(fileName, () -> new ByteArrayInputStream(macroText.getBytes(StandardCharsets.UTF_8))));
        saveAnchor.setVisible(true);
    }
}
