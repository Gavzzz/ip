package Duke.commands;

import java.util.ArrayList;
import java.util.List;
import Duke.*;
import java.io.IOException;


public class UnmarkCommands extends TaskCommands {


    /**
     * Class constructor.
     *
     * @param input The input provided by the user.
     */


    public UnmarkCommands(String input) throws DukeException {
        super(input);
    }


    /**
     * Executes the command to unmark a task in the task list.
     *
     * @param tasks The task list containing all the tasks before the command is executed.
     * @param ui Provides access to the UI of the program.
     * @param storage Provides access to local storage.
     * @throws IOException when there is a problem with the IO.
     * @throws DukeException when there is a wrong input or save file issues.
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            String line1 = "I've marked this task as not done (yet ;))";
            tasks.markAsNotDone(taskNumber);
            storage.updateTask(taskNumber, Constants.UNMARK);
            String line2 = tasks.getTaskToString(taskNumber);
            String reply = line1 + line2;
            return reply;
        } else {
            throw new DukeException("tasks.Task does not exist.");
        }
    }
}
