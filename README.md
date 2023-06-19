# Onitama Game

## Compilação (estando no diretório raiz do projeto)
1. Execute o comando de compilação:
   javac -d out -cp .:./lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar *.java

## Execução:
2. Após a conclusão da compilação, execute o programa usando o comando:

   java -cp .:./lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar:./out GameImpl

Aviso: Nenhuma classe minha possui um método main. Para testar usei apenas o JUnit.

## Execução dos testes (JUnit):
1. Execute o comando para compilar os testes:

   javac -d out -cp .:./lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar test/*.java

2. Em seguida, execute o comando para rodar os testes:

   java -cp .:./lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar:./out org.junit.runner.JUnitCore NomeDoTeste

   Certifique-se de substituir "NomeDoTeste" pelo nome da classe de teste que deseja executar.

### Testes disponíveis (dentro da pasta test):
- CardTest.java
- GameImplTest.java
- PieceTest.java
- PlayerTest.java
- PositionTest.java
- SpotTest.java
