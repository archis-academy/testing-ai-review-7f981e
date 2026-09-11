public class Scroll {

	public static void main(String[] args) throws InterruptedException {
		
		// TODO Auto-generated method stub

		//Assignment: no of row, no of column, 2 row value, sum the value of 3rd clmn of table 
		//& type 3 letter & move the cursor pointing to one option & verify the option is choosen
	
	WebDriver driver = new ChromeDriver();
	driver.get("https://rahulshettyacademy.com/AutomationPractice/");
	int n = 3;
	int sum = 0;
	String opt = "uni";
	JavascriptExecutor  js= (JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,600)");
	System.out.println("No of rows in the table is " + driver.findElements(By.xpath("//table[@name='courses']//tbody/tr")).size());
	System.out.println("No of columns in the table is " + driver.findElements(By.xpath("//table[@name='courses']//tbody/tr[1]//th")).size());
	for(int i=0; i<driver.findElements(By.xpath("//table[@name='courses']//tbody//tr")).size(); i++)
	{
		if(n==i) {
			for(int j=0; j<driver.findElements(By.xpath("//table[@name='courses']//tbody//tr["+n+"]//td")).size(); j++ )
			{
				System.out.println(driver.findElements(By.xpath("//table[@name='courses']//tbody//tr["+n+"]//td")).get(j).getText());
				
			}
			
		}
		if(i>1)
		{
			sum = sum + Integer.parseInt(driver.findElements(By.xpath("//table[@name='courses']//tbody//tr["+i+"]//td")).get(2).getText());
		}
	}
	System.out.println("total sum of the price is "+ sum);
	js.executeScript("window.scrollBy(0,100)");
	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
	w.until(ExpectedConditions.visibilityOfElementLocated(By.id("autocomplete")));
	driver.findElement(By.id("autocomplete")).sendKeys(opt);
	Actions a = new Actions(driver);
	w.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-menu-item")));
	a.moveToElement(driver.findElement(By.xpath("//li[@class='ui-menu-item'] //div[text()='United Kingdom (UK)']"))).click().build().perform();
	//pending
	w.until(ExpectedConditions.attributeContains(By.id("autocomplete"), "value", "United Kingdom (UK)"));
	//System.out.println(a.moveToElement(driver.findElement(By.className("ui-autocomplete-input"))).doubleClick().keyDown(Keys.CONTROL)
			 //.sendKeys("a")
		//	 .keyUp(Keys.CONTROL).toString().contains("United Kingdom (UK)"));
	WebElement suggestionBox = driver.findElement(By.id("autocomplete"));
	String selectedCountry = suggestionBox.getAttribute("value");
	Assert.assertEquals(selectedCountry, "United Kingdom (UK)");
	
	}
}