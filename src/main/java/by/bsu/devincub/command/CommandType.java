package by.bsu.devincub.command;

import by.bsu.devincub.command.impl.CountCommand;
import by.bsu.devincub.command.impl.RichestCommand;
import by.bsu.devincub.command.impl.DefaultPageCommand;

public enum CommandType {
    GET_RICHEST {
        {
            this.command = new RichestCommand();
        }
    },
    GET_SUM {
        {
            this.command = new CountCommand();
        }
    },
    GO_TO_DEFAULT {
        {
            this.command = new DefaultPageCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
