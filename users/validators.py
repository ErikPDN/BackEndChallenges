from django.core.exceptions import ValidationError

def validate_cpf(value):
    # Remover pontuação
    cpf = ''.join([char for char in value if char.isdigit()])
    print(f'CPF Limpo: {cpf}')

    # Verificar se tem 11 dígitos
    if len(cpf) != 11:
        raise ValidationError('CPF inválido: deve conter 11 dígitos')
    
    # Verificar se todos os dígitos são iguais
    if cpf == cpf[0] * 11:
        raise ValidationError('CPF inválido: todos os dígitos são iguais')
    
    # Função para calcular os dígitos verificadores
    def calcular_digito(cpf, multiplicadores):
        soma = sum(
            int(cpf[i]) * multiplicadores[i]
            for i in range(len(multiplicadores))
        )
        resto = soma % 11
        return 0 if resto < 2 else 11 - resto
    
    # Multiplicadores para o primeiro dígito verificador 
    multiplicadores_primeiro = list(range(10, 1, -1))
    primeiro_digito = calcular_digito(cpf, multiplicadores_primeiro)
    print(f'Primeiro Dígito Calculado: {primeiro_digito}')

    # Multiplicadores para o segundo dígito verificador
    multiplicadores_segundo = list(range(11, 1, -1))
    segundo_digito = calcular_digito(cpf[:-1] + str(primeiro_digito), multiplicadores_segundo)
    print(f'Segundo Dígito Calculado: {segundo_digito}')

    # Verifica se os dígitos calculados são iguais aos informados
    print(f'CPF Final: {cpf[-2]} {cpf[-1]}')
    if not (cpf[-2] == str(primeiro_digito) and cpf[-1] == str(segundo_digito)):
        raise ValidationError(f'CPF inválido: {cpf} {primeiro_digito} {segundo_digito}')
