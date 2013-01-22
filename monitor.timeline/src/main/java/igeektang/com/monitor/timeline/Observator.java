package igeektang.com.monitor.timeline;

import java.util.List;

public interface Observator {
    PluginInfo getDescription();
    List<Event> analyze();
}
