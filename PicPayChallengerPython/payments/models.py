from django.db import models
from decimal import Decimal
from users.models import User

class Transactions(models.Model):
    amount = models.DecimalField(max_digits=15, decimal_places=2, default=Decimal('0.00'), editable=False)
    payer = models.ForeignKey(User, on_delete=models.DO_NOTHING, editable=False)
    payee = models.ForeignKey(User, on_delete=models.DO_NOTHING, editable=False, related_name='payee_user')
    date = models.DateTimeField(auto_now_add=True)

    def __str__(self) -> str:
        return f'from {self.payer.cpf} to {self.payee.cpf} - R$ {self.amount}'