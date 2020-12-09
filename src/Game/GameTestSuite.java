package Game;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({Doors.DoorsTestSuite.class, Containers.ContainersTestSuite.class, Location.LocationTestSuite.class,
        Commands.CommandsTestSuite.class, Characters.CharactersTestSuite.class})

public class GameTestSuite {
}