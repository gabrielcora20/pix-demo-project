db = db.getSiblingDB("pix");

db.createCollection("payment");
db.payment.createIndex({ inclusionDate: -1 });
db.payment.createIndex({ status: 1 });