package debtechllc.deb.sonderblu.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubscriptionExistingPlansResponseDM {
    @SerializedName("customer")
    private Customer customer;
    @SerializedName("status")
    private StatusClass statusClass;
    @SerializedName("plans")
    private Plans plans;

    public SubscriptionExistingPlansResponseDM(Customer customer, Plans plans) {
        this.customer = customer;
        this.plans = plans;
        this.statusClass = statusClass;
    }

    public Plans getPlans() {
        return plans;
    }

    public void setPlans(Plans plans) {
        this.plans = plans;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StatusClass getStatusClass() {
        return statusClass;
    }

    public void setStatusClass(StatusClass statusClass) {
        this.statusClass = statusClass;
    }

    public static class Plans {
        @SerializedName("count")
        private String count;
        @SerializedName("items")
        private List<Items> items;

        public Plans(String count, List<Items> items) {
            this.count = count;
            this.items = items;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<Items> getItems() {
            return items;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }
    }
    public static class Items {
        @SerializedName("name")
        private String name;

        public Items(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Customer {
        @SerializedName("userId")
        private String userId;
        @SerializedName("billingInfo")
        private BillingInfo billingInfo;
        @SerializedName("subscription")
        private Subscription subscription;

        public Customer(String userId, BillingInfo billingInfo, Subscription subscription) {
            this.userId = userId;
            this.billingInfo = billingInfo;
            this.subscription = subscription;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public BillingInfo getBillingInfo() {
            return billingInfo;
        }

        public void setBillingInfo(BillingInfo billingInfo) {
            this.billingInfo = billingInfo;
        }

        public Subscription getSubscription() {
            return subscription;
        }

        public void setSubscription(Subscription subscription) {
            this.subscription = subscription;
        }
    }
    public static class BillingInfo {
        @SerializedName("card")
        private Card card;
        @SerializedName("fullName")
        private String fullName;
        @SerializedName("address")
        private String address;
        @SerializedName("city")
        private String city;
        @SerializedName("zip")
        private String zip;
        @SerializedName("countryCode")
        private String countryCode;

        public BillingInfo(Card card, String fullName, String address, String city, String zip, String countryCode) {
            this.card = card;
            this.fullName = fullName;
            this.address = address;
            this.city = city;
            this.zip = zip;
            this.countryCode = countryCode;
        }

        public Card getCard() {
            return card;
        }

        public void setCard(Card card) {
            this.card = card;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }
    }

    public static class Subscription {
        @SerializedName("name")
        private String name;
        @SerializedName("code")
        private String code;
        @SerializedName("stripePlanId")
        private String stripePlanId;
        @SerializedName("stripeProductId")
        private String stripeProductId;
        @SerializedName("stripeSubscriptionId")
        private String stripeSubscriptionId;
        @SerializedName("subscribedAt")
        private String subscribedAt;

        public Subscription(String name,String code, String stripePlanId, String stripeProductId, String stripeSubscriptionId, String subscribedAt) {
            this.name = name;
            this.code = code;
            this.stripePlanId = stripePlanId;
            this.stripeProductId = stripeProductId;
            this.stripeSubscriptionId = stripeSubscriptionId;
            this.subscribedAt = subscribedAt;
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getStripePlanId() {
            return stripePlanId;
        }

        public void setStripePlanId(String stripePlanId) {
            this.stripePlanId = stripePlanId;
        }

        public String getStripeProductId() {
            return stripeProductId;
        }

        public void setStripeProductId(String stripeProductId) {
            this.stripeProductId = stripeProductId;
        }

        public String getStripeSubscriptionId() {
            return stripeSubscriptionId;
        }

        public void setStripeSubscriptionId(String stripeSubscriptionId) {
            this.stripeSubscriptionId = stripeSubscriptionId;
        }

        public String getSubscribedAt() {
            return subscribedAt;
        }

        public void setSubscribedAt(String subscribedAt) {
            this.subscribedAt = subscribedAt;
        }
    }


    public static class Card {
        @SerializedName("number")
        private String number;
        @SerializedName("expMonth")
        private int expMonth;
        @SerializedName("expYear")
        private int expYear;

        public Card(String number, int expMonth, int expYear) {
            this.number = number;
            this.expMonth = expMonth;
            this.expYear = expYear;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getExpMonth() {
            return expMonth;
        }

        public void setExpMonth(int expMonth) {
            this.expMonth = expMonth;
        }

        public int getExpYear() {
            return expYear;
        }

        public void setExpYear(int expYear) {
            this.expYear = expYear;
        }
    }

    public static class StatusClass {
        @SerializedName("status")
        private String status;

        public StatusClass(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
