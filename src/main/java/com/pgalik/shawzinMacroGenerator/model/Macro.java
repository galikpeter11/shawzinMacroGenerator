package com.pgalik.shawzinMacroGenerator.model;

import com.pgalik.shawzinMacroGenerator.model.action.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Macro {
    private String description;
    private String comment;
    private List<Action> actions;

    @Override
    public String toString() {
        final String actionsString = actions.stream()
                .map(Action::toString)
                .collect(Collectors.joining("\n"));
        return "<Root>\n" +
                "\t<DefaultMacro>\n" +
                "\t\t<Major></Major>\n" +
                "\t\t<Description>" + description + "</Description>\n" +
                "\t\t<Comment>" + comment + "</Comment>\n" +
                "\t\t<GUIOption>\n" +
                "\t\t\t<RepeatType>0</RepeatType>\n" +
                "\t\t</GUIOption>\n" +
                "\t\t<KeyUp>\n" +
                "\t\t\t<Syntax></Syntax>\n" +
                "\t\t</KeyUp>\n" +
                "\t\t<KeyDown>\n" +
                "\t\t\t<Syntax>\n" +
                actionsString + "\n" +
                "SayString\n" +
                "\t\t\t</Syntax>\n" +
                "\t\t</KeyDown>\n" +
                "\t\t<Software>Warframe</Software>\n" +
                "\t</DefaultMacro>\n" +
                "</Root>\n";
    }
}
