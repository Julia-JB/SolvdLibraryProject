public class QuoteThread implements Runnable{
	@Override
	public void run() {
		QuotesApiClient.getRandomQuote();
	}
}
