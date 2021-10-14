/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priorities;

import static priorities.DataCharacter.maxInfect;

/**
 *
 * @author b15-04m
 */
class Smith extends Character {
        
        private int infect;

        public Smith() {
            this.name = "Smith";
            this.location = null;
            this.age = -1;
            this.death = -1;          
            this.infect = 0;
        }
        
        public void updateInfection(){
            infect = RandomNumber.randomNumber(1, maxInfect);
        }
    
    }
