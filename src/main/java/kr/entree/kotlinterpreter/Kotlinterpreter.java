package kr.entree.kotlinterpreter;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.kotlin.cli.common.environment.UtilKt;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.logging.Level;

/**
 * Created by JunHyung Lim on 2020-03-16
 */
public class Kotlinterpreter extends JavaPlugin {
    private final ScriptEngine scriptEngine;

    public Kotlinterpreter() {
        Thread.currentThread().setContextClassLoader(getClassLoader());
        scriptEngine = new ScriptEngineManager().getEngineByExtension("kts");
        UtilKt.setIdeaIoUseFallback();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            Object result = scriptEngine.eval(StringUtils.join(args, ' '));
            if (result != null) {
                sender.sendMessage(result.toString());
            }
        } catch (ScriptException e) {
            getLogger().log(Level.WARNING, e, () -> "Exception while evaluating kscript.");
        }
        return true;
    }
}
