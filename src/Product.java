public class Product {
        private String itemName;
        private double ItemPrice;

        public Product(String itemName, double ItemPrice) {
            this.itemName = itemName;
            this.ItemPrice = ItemPrice;
        }

        public String getItemName() {
            return itemName;
        }

        public double getItemPrice() {
            return ItemPrice;
        }
}
