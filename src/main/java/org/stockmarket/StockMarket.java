package org.stockmarket;

import org.stockmarket.model.Stock;
import org.stockmarket.model.StockType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StockMarket {
    private final List<Stock> stocks;

    public StockMarket() {
        stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public List<String> listStocksByType(StockType type) {
        return stocks.stream()
                .filter(stock -> stock.getType() == type)
                .map(stock -> stock.getSymbol() +" - " + stock.getTypeDescription() +" - $" + stock.getPrice())
                .collect(Collectors.toList());
    }
    public void updateStockPrice(String symbol, double newPrice) {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equals(symbol)) {
                stock.setPrice(newPrice);
                return;
            }
        }
    }
    public Optional<Stock> getStock(String symbol) {
        for (Stock stock : stocks ) {
            if ( stock.getSymbol().equals(symbol)) {
                return Optional.of(stock);
            }
        }
        return Optional.empty();
    }
    public List<Stock> getStocks() {return new ArrayList<>(stocks);}
}
