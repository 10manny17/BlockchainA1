import java.util.ArrayList;

public class Blockchain {
	// Add blockchain properties here
	public ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;

	/**
	 * Constructor
	 */
	public Blockchain() {
		blockchain.add(Block.genesis());
	}

	public ArrayList<Block> getBlockchain() {
		return this.blockchain;
	}

	public void setBlockchain(ArrayList<Block> Blocks) {
		this.blockchain = Blocks;
	}

	/**
	 * Adds a new block to the blockchain
	 * 
	 * @param data Block data
	 */
	public void addBlock(String data) {
		blockchain.add(new Block("0", blockchain.get(blockchain.size() - 1).getHash(), data, 0, 4));
	}

	/**
	 * Prints out all blocks of a blockchain
	 */
	public void print() {
		for (int i = 0; i < blockchain.size(); i++) {
			System.out.printf("Block #%d: " + blockchain.get(i).toString() + "%n", i);
		}
	}

	/**
	 * Checks whether a blockchain is valid
	 * Hints:
	 * - Genesis block must be valid
	 * - Hash of each block on the chain (except genesis block) must be valid
	 * - The current's prevHash must match the previous block's hash
	 * 
	 * @param blockchain The blockchain to check
	 * @return True if valid, false otherwise
	 */
	public static boolean isValidChain(Blockchain blockchain) {
		Block currBlock;
		Block prevBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		for (int i = 1; i < blockchain.getBlockchain().size(); i++) {
			currBlock = blockchain.getBlockchain().get(i);
			prevBlock = blockchain.getBlockchain().get(i - 1);
			if (!currBlock.hash.equals(currBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}

			if (!prevBlock.hash.equals(currBlock.prevHash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}

			if (!currBlock.hash.equals(currBlock.hash.substring(0, difficulty).equals(hashTarget))) {
				System.out.println("This block hasn't been mined");
				return false;
			}

		}

		return true;
	}
}
