/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai.core;

import rts.GameState;
import rts.PlayerAction;

/**
 *
 * @author santi
 * 
 * A "InterruptibleAI" is one that can divide the computation across multiple game frames. As such, it must implement the three
 * basic methods described below, does cannot modify the getAction method
 */
public abstract class InterruptibleAIWithComputationBudget extends AIWithComputationBudget {
    
    public InterruptibleAIWithComputationBudget(int mt, int mi) {
        super(mt,mi);
    }
    
    public final PlayerAction getAction(int player, GameState gs) throws Exception
    {
        if (gs.canExecuteAnyAction(player)) {
            startNewComputation(player,gs);
            computeDuringOneGameFrame();
            return getBestActionSoFar();
        } else {
            return new PlayerAction();        
        }       
    }
    
    public abstract void startNewComputation(int player, GameState gs);
    public abstract void computeDuringOneGameFrame() throws Exception;
    public abstract PlayerAction getBestActionSoFar() throws Exception;
    
}
