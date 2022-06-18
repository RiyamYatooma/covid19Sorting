/***
 * This class represents one of the 50 states in the united states with attributes 
 *
 * @author Riyam Yatooma
 * @version 09/17/2021
 **/
public class State {

	private String name;
	private String capital;
	private String region;
	private int UsHouseSeats;
	private int population;
	private double covidcases;
	private double coviddeaths;
	private int householdincome;
	private double violentcrimerate;
	private double caseRate;
	private double deathRate;
	private double caseFatalityRate;

	/**
	 * Construct a new state object including all needed attributes 
	 * 
	 * @param name the name of the state
	 * @param capital the capital of the state
	 * @param region the region of the state
	 * @param usHouseSeats the number of house seats of the state
	 * @param population the population of the state
	 * @param covidcases the number of covid cases 
	 * @param coviddeaths the number of covid deaths 
	 * @param householdincome the house income of the state
	 * @param violentcrimerate the violent crime rate of the state
	 *
	 */
	public State(String name, String capital, String region, int usHouseSeats, int population, double covidcases,
			double coviddeaths, int householdincome, double violentcrimerate) {

		super();
		this.name = name;
		this.capital = capital;
		this.region = region;
		this.UsHouseSeats = usHouseSeats;
		this.population = population;
		this.covidcases = covidcases;
		this.coviddeaths = coviddeaths;
		this.householdincome = householdincome;
		this.violentcrimerate = violentcrimerate;

		this.caseRate = (double) this.covidcases / this.population * 100000;
		this.deathRate = (double) this.coviddeaths / this.population * 100000;
		this.caseFatalityRate = (double) this.deathRate / this.caseRate;
	}

	/**
	 * Gets the name of the states
	 * 
	 * @return the name of the states
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the states
	 * 
	 * @return name of state
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of the capital
	 * 
	 * @return the name of the capital
	 */

	public String getCapital() {
		return capital;
	}

	/**
	 * Sets the Capital name
	 * 
	 * @param  a string of capital
	 */

	public void setCapital(String capital) {
		this.capital = capital;
	}

	/**
	 * Gets the region name
	 * 
	 * @return region name
	 */

	public String getRegion() {
		return region;
	}

	/**
	 * sets the region name
	 * 
	 * @param region the name of region
	 */

	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Gets the number of the US House Seats 
	 * 
	 * @return the numbers of the USHouse seats
	 */

	public int getUsHouseSeats() {
		return UsHouseSeats;
	}

	/**
	 * Sets the number of the USHouseSeats
	 * 
	 * @param usHouseSeats the number of the usHouseSeats
	 */

	public void setUsHouseSeats(int usHouseSeats) {
		UsHouseSeats = usHouseSeats;
	}

	/**
	 * Gets the number of the population
	 * 
	 * @return the number of the population
	 */

	public double getPopulation() {
		return population;
	}

	/**
	 * Sets the population as a number 
	 * 
	 * @param  population the population number 
	 */

	public void setPopulation(int population) {
		this.population = population;
	}

	/**
	 * Gets the number of covidcases 
	 *
	 * @return the number of covidcases
	 */

	public double getCovidcases() {
		return covidcases;
	}

	/**
	 * Sets the vovidcases as a double 
	 * 
	 * @param covidcases as a double
	 */

	public void setCovidcases(double covidcases) {
		this.covidcases = covidcases;
	}

	/**
	 * Gets the caseFetalityRate
	 * 
	 * @return the caseFetalityRate
	 */
	public double getCaseFatalityRate() {
		return caseFatalityRate;
	}

	/**
	 * Gets the deathRate 
	 * 
	 * @return the deathRate
	 */
	public double getDeathRate() {
		return deathRate;
	}

	/**
	 * Gets the covidDeaths
	 * 
	 * @return coviddeaths
	 */
	public double getCoviddeaths() {
		return coviddeaths;
	}

	/**
	 *  Sets coviddeaths
	 *  
	 * @param coviddeaths as a double
	 * 
	 */

	public void setCoviddeaths(double coviddeaths) {
		this.coviddeaths = coviddeaths;
	}

	/**
	 * Gets the householdincome
	 * 
	 * @return householdincome
	 */

	public int getHouseholdincome() {
		return householdincome;
	}

	/**
	 * Sets the houseHoldincome 
	 * 
	 * @param householdincome as an int 
	 */

	public void setHouseholdincome(int householdincome) {
		this.householdincome = householdincome;
	}

	/**
	 * gets the violentcrimerate 
	 * 
	 * @return the violentcrimerate
	 */

	public double getViolentcrimerate() {
		return violentcrimerate;
	}

	/**
	 * sets the crimerate value 
	 * @param violentcrimerate as a double
	 */

	public void setViolentcrimerate(double violentcrimerate) {
		this.violentcrimerate = violentcrimerate;
	}

	/**
	 * Method that prints out all information of the states
	 */
	public void printState() {

		System.out.println("Name:      " + this.name);
		System.out.println("MHI:       " + this.householdincome);
		System.out.println("VCR:       " + this.violentcrimerate);
		System.out.printf("CFR:       %.6f%n", this.caseFatalityRate);
		System.out.printf("CaseRate:  %.2f%n", this.caseRate);
		System.out.printf("DeathRate: %.2f%n%n", this.deathRate);
	}

	/**
	 * Method that formats the data from the csv file
	 */
	public void printStateLn() {
		// int/long %d float/double %f //remove before submit
		System.out.printf("%-17s %-17d %-17.2f %-10.6f %15.2f %15.2f %n", this.name, this.householdincome,
				this.violentcrimerate, this.caseFatalityRate, this.caseRate, this.deathRate);

	}

}
