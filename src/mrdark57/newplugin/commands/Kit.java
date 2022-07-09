package mrdark57.newplugin.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Kit implements CommandExecutor {

    // CommandSender representa lo que sea que esté enviando el comando. Podría ser un Player, ConsoleCommandSender o BlockCommandSender
    //El comando representa lo que es el comando que se llama. Esto casi siempre se sabrá con anticipación.
    //La etiqueta representa la primera palabra exacta del comando (excluyendo los argumentos) que ingresó el remitente
    //Args es el resto de la instrucción del comando (excluyendo la etiqueta) dividido por espacios y colocado en una matriz.

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Crear un nuevo stack de items (diamante)
            ItemStack diamond = new ItemStack(Material.DIAMOND,10);

            // Crear un nuevo stack de items (ladrillo)
            ItemStack bricks = new ItemStack(Material.BRICK,20);

            // Le da al jugador los items (la coma separa la lista de todos los stacks)
            player.getInventory().addItem(bricks, diamond);
        }

        // Si el jugador (o consola) uses our command correct, we can return true
        return true;
    }
}
