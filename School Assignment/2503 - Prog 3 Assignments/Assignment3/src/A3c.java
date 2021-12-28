import java.util.*;
/** 
 * WORKING CODE
 * COMP 2503 Winter 2018 Assignment A3c 
 * @author Rafael Angelo Pucut
 * @date 3/26/2018
 * 
 * This program reads a text file which contains
 * transactions of buying and selling shares which results 
 * in calculating capital gain
 */
public class A3c {

	private Scanner inp = new Scanner(System.in);
	private int capGain = 0;
	private int shareTracker = 0;

	private Queue<Integer> transactions = new Queue();
	private Queue<Integer> unitDollar = new Queue();
	
	private Queue<Integer> transferUnits = new Queue();
	private Queue<Integer> transferDollar = new Queue();
	
	private Queue<Integer> sellTransactions = new Queue();
	private Queue<Integer> sellAmount = new Queue();
	
	private String action = "";
	private String quantity = "";
	private String cost = "";
	
	/**
	 * Runs the whole program
	 * @param args
	 */
	public static void main(String[] args)
	{
		A3c running = new A3c();
		running.capitalRun();
		running.selling();
		System.out.println("Capital Gain: " + running.capGain);
	}
	
	/**
	 * Reads the file with transactions
	 * and adds the share amount and price
	 * to the corresponding queues whether its 
	 * buying or selling
	 */
	public void capitalRun()
	{
		while(inp.hasNext())
		{
			action = inp.next();
			quantity = inp.next();
			cost = inp.next();
			int amount = Integer.parseInt(quantity);
			int unitValue = Integer.parseInt(cost);
			if (action.equalsIgnoreCase("Buy"))
			{
				buyShares(amount, unitValue);
			}
			else if (action.equalsIgnoreCase("Sell"))
			{
				sellTransactions.enqueue(amount);
				sellAmount.enqueue(unitValue);
			}
		}
	}
	
	/**
	 * Places the buying transaction data into 2 queues
	 * which separately records shares bought and price
	 * of those shares
	 * @param buyUnits is the amount of shares bought
	 * @param buyPrice is the price of buying shares
	 */
	public void buyShares(int buyUnits, int buyPrice)
	{
		shareTracker += buyUnits;
		transactions.enqueue(buyUnits);
		unitDollar.enqueue(buyPrice);
	}
	
	/**
	 * Sells the shares after bought transactions have occurred
	 * by accessing the selling queues data of amount of shares to
	 * be sold and price of the sold shares
	 */
	public void selling()
	{
		while(!sellTransactions.isEmpty() && !sellAmount.isEmpty())
		{
			int sellingShares = sellTransactions.dequeue();
			int sellCost = sellAmount.dequeue();
			capitalGain(sellingShares, sellCost);
		}
	}
	
	/**
	 * Calculates the capital gain
	 * @param sellUnits is the amount of shares to be sold
	 * @param sellPrice is the price of each individual share to be sold
	 */
	public void capitalGain(int sellUnits, int sellPrice)
	{
		//CAPITAL GAIN = CAPITAL GAIN + SHARES TO SELL * (SELLING PRICE - BUYING PRICE)

		int buyingCost = 0;
		int numberOfShares = 0;
		
		while(sellUnits != 0)
		{	
			//the conditional below assures that 
			//we are accessing the oldest stocks that need to be sold
			//if there are still shares to be sold
			if (!transferUnits.isEmpty() && !transferDollar.isEmpty())
			{
				numberOfShares = transferUnits.dequeue();
				buyingCost = transferDollar.dequeue();
			}
			else
			{
				numberOfShares = transactions.dequeue();
				buyingCost = unitDollar.dequeue();
			}
			
			int remaining = 0;
			
			
			//makes sure that there are enough merchandise in stock
			//to be sold before calculating capital gain
			if (sellUnits > shareTracker)
			{
				System.out.println("You do not have enough shares to sell");
			}
			else if (numberOfShares > sellUnits)
			{
				remaining = numberOfShares - sellUnits;
				capGain += sellUnits * (sellPrice - buyingCost);
				shareTracker -= sellUnits;
				transferUnits.enqueue(remaining);
				transferDollar.enqueue(buyingCost);
				sellUnits = 0;
			}
			else if (numberOfShares == sellUnits)
			{
				capGain += sellUnits * (sellPrice - buyingCost);
				shareTracker -= sellUnits;
				sellUnits = 0;
			}
			else
			{
				remaining = sellUnits - numberOfShares;
				shareTracker -= numberOfShares;
				capGain += numberOfShares * (sellPrice - buyingCost);
				sellUnits = remaining;
			}
		}
	}
}
