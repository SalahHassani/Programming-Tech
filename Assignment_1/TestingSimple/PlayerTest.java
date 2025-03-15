package Assignment_1.TestingSimple;

import Assignment_1.Simple.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

class PlayerTest {

    private Player player;
    
    private CyclicDoublyLink board;
    private int initialBalance = 10000;

    @BeforeEach
    void setUp() {
        board = new CyclicDoublyLink();
        for (int i = 0; i < 5; i++) {
            board.addParentNode();
            CyclicDoublyLink childList = new CyclicDoublyLink();
            for (int j = 0; j < 5; j++) {
                childList.addChildNode(i+j, Field.Property);
            }
            ((ParentNode) board.tail).childList = (ChildNode) childList.head;
    }
        board.setUpAndDown();

        player = new Player("TestPlayer", "Greedy", 1, initialBalance, 0, 0, board);
    }

    @Test
    void testInitialPositionAndStrategyType() {
        assertEquals(0, player.getPosX());
        assertEquals(0, player.getPosY());

        assertEquals(player.getStrategyType(), "Greedy");
        assertEquals(player.getName(), "TestPlayer");

        assertEquals(initialBalance , player.getBalance()); // because initially player brought a property for 1000;

    }

    @Test
    void testBalanceAfterLuckyField() {
        // Set the player on a lucky field
        ChildNode luckyNode = new ChildNode(Field.Lucky);
        player.setCurrentNode(luckyNode);
        player.checkAndBalance();
        assertEquals(initialBalance + Field.Lucky.getLuckyBounus(), player.getBalance()); 
    }

    @Test
    void testBalanceAfterServiceFee() {
        // Set the player on a service field
        ChildNode serviceNode = new ChildNode(Field.Service);
        serviceNode.setOwner(null);
        player.setCurrentNode(serviceNode);
        player.checkAndBalance();
        assertEquals(initialBalance - Field.Service.getServiceFee(), player.getBalance() - Field.Lucky.getLuckyBounus());  // since lucky bonus is extra...
    }

    @Test
    void testBankruptcy() {
        // Make the player lose all balance
        player.setBalance(-initialBalance);
        player.checkAndBalance();
        assertTrue(player.getBankcrupcyStatus());
        assertEquals(0, player.getBalance());
    }

    @Test
    void testPropertyOwnership() {
        ChildNode propertyNode = new ChildNode(Field.Property);
        propertyNode.setOwner(null);
        player.setCurrentNode(propertyNode);
        player.checkAndBalance();
        assertEquals(initialBalance - 1000, player.getBalance());
        assertEquals(player, propertyNode.getOwner());
    }

    @Test
    void testHouseBuilding() {
        ChildNode propertyNode = new ChildNode(Field.Property);
        propertyNode.setOwner(player);
        player.setCurrentNode(propertyNode);

        player.checkAndBalance(); 
        assertTrue(propertyNode.getHouseBuildStatus());
        assertEquals(initialBalance - 4000, player.getBalance()); 
    }

    @Test
    void testWinner() {

        // resetting the board
        setUp();

        Menu menu = new Menu(board, 5, 5, 2, new ArrayList<>(Arrays.asList("SHassani", "JJAbki")), 2, new ArrayList<>(Arrays.asList(5,2,5, 1)), 42, new ArrayList<>(Arrays.asList("Tactical", "Greedy")));

        
        menu.run();
        Player winner = menu.getWinner();
        assertEquals("SHassani", winner.getName()); 
        assertFalse(player.getBankcrupcyStatus());
        assertEquals(5500, winner.getBalance()); 
        assertEquals(5, winner.getOwnedProperties().size()); 

    }
}
