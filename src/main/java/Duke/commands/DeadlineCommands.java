package Duke.commands;


import java.util.ArrayList;
import java.util.List;
import Duke.*;
import Duke.tasks.Deadline;
import Duke.tasks.Task;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommands extends Executor {

    private final static Pattern deadlinePattern = Pattern.compile("(?<taskName>.*) /by (?<by>.*)");

    /**
     * Class constructor.
     *
     * @param input The user input.
     */

    public DeadlineCommands(String input) {
        super(input);
        assert input.startsWith("deadline");
    }


    /**
     * Executes the command to create a new Deadline task, saves the new task in the task list and
     * prints the size of the list after the command execution.
     *
     * @param tasks The task list containing all the tasks before the command is executed.
     * @param ui Provides access to the UI of the program.
     * @param storage Provides access to local storage.
     * @throws IOException when there is a problem with the IO.
     * @throws DukeException when there is a wrong input or save file issues.
     */

    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        ArrayList<String> text = new ArrayList<>();
        Matcher match = deadlinePattern.matcher(description);
        if (!match.matches()) {
            throw new DukeException("No deadline was given, try again");
        }
        text.add("I've added this task:");
        Task addedTask = tasks.addTask(new Deadline(match.group("taskName"), match.group("by")));
        text.add(addedTask.toString());
        text.addAll(super.execute(tasks, ui, storage));
        return text;
    }
}
