package business.services;

import business.models.Auction;
import business.models.Bid;
import business.models.Bidder;
import business.models.Item;
import persistance.repos.AuctionRepo;
import persistance.repos.BidderRepo;
import persistance.repos.ItemRepo;
import persistance.repos.UserRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class BidderService {
    private static AuditService auditService;

    static {
        auditService = AuditService.getInstance();
    }
    private BidderService() {}
    public static void increaseBalance(Bidder bidder, Float ammount) throws SQLException {
        bidder.setBalance(bidder.getBalance() + ammount);
        BidderRepo repo = new BidderRepo();
        repo.update(bidder);
        auditService.logAction();
    }
    public static List<Item> getItems(Bidder bidder) throws SQLException {
        List <Item> items = ItemRepo.getInstance().getItemsByBidder(bidder.getId());
        auditService.logAction();
        return items;
    }
    public static List<Auction> getAuctions() throws SQLException {
        List <Auction> auctions = AuctionRepo.getInstance().getAvailableAuctions();
        auditService.logAction();
        return auctions;
    }
//
//    public static void AddNewItem(String title, String description, float startingPrice, float currentPrice, boolean sold)
//    {
//        Bid bid = new Bid(-1, 3, startingPrice, currentPrice, sold);
//        Item item = new Item(title, description, bid);
//    }
}
