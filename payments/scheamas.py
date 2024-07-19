from ninja import ModelSchema

from payments.models import Transactions

class TransactionSchema(ModelSchema):
    class Meta:
        model = Transactions
        exclude = ["id", "date"]