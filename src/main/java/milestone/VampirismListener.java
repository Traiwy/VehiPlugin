package milestone;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class VampirismListener implements Listener {
    @EventHandler
    public void onVampirismEvent(EntityDamageByEntityEvent e){
        if(!(e.getDamager() instanceof Player)) return;

    }
}
