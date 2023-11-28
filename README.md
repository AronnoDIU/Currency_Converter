# Currency Converter

This is a simple Java application that converts currency from one type to another using the latest exchange rates from
the ExchangeRate-API.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Usage](#usage)
- [Features](#features)
- [Limitations](#limitations)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

- Java Development Kit (JDK) 8 or above
- An API key from [ExchangeRate-API](https://www.exchangerate-api.com/)

## Usage

1. Clone the repository to your local machine.
2. Replace the `API_KEY` in the `CurrencyConverter.java` file with your own API key from ExchangeRate-API.
3. Compile and run the `CurrencyConverter.java` file.
4. Follow the prompts in the console to enter the source currency code, target currency code, and the amount to convert.

## Features

- Fetches the latest exchange rates from the ExchangeRate-API.
- Convert any amount from one currency to another.
- Handles invalid currency codes and network errors gracefully.

## Limitations

- The application requires an active internet connection to fetch the latest exchange rates.
- The accuracy of the conversion depends on the accuracy of the exchange rates provided by the ExchangeRate-API.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.