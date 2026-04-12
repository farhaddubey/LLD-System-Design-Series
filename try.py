class Product: 
    def __init__(self, id, name, price, shipping_cost):
        self.id=id
        self.name=name 
        self.price=price 
        self.shipping_cost=shipping_cost
class Node: 
    def __init__(self, product, quantity):
        self.product=product
        self.quanity=quantity 
class User: 
    def __int__(self, id, name, balance):
        self.id=id
        self.name=name 
        self.balance=balance 
        self.orders=[] 
class Company: 
    def __init__(self):
        self.products=[] #list of Nodes (product, quantity)
        self.users=[]
    def add_product(self, product, quantity): 
        self.products.append(Node(product, quantity))
    def add_user(self, user): 
        self.users.append(user) 
    def get_products(self): 
        return self.products 
    def get_users(self): 
        return self.users 
    def make_order(self, order_products, user) 
        total_cost=0 
        max_shipping=0 
        # // STEP 1. CHECKIGN AVAILABILITY 
        for order in order_products: 
            found=False 
            for p in self.products: 
                if p.product.id==order.product.id: 
                    if p.quantity<order.quanity: 
                        return # Not enough stock 
                    found = True 
                    break 
            if not found: 
                return 
        # STEP 2: CALCULATE COST 
        for order in order_products: 
            total_cost+=order.product.price*order.quantity 
            max_shipping=max(max_shipping, order.product.shipping_cost)
        total_cost+=max_shipping
        # STEP 3. CHECK BALANCE 
        if user.balance< total_cost: 
            return 
        # STEP 4. Update System 
        user.balance-=total_cost 
        for order in order_products: 
            for p in self.products: 
                if p.product.id==order.product.id: 
                    p.quantity-=order.quantity
            user.orders.append(order) 