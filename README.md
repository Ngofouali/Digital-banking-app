<h3>1. Création du projet Spring Boot avec les dépendances</h3>
<img src="captures/creation-projet.png">
<br/>

<h3>2. Création des entités JPA : Customer, BankAccount, SavingAccount, CurrentAccount, AccountOperation</h3>
<ul>
<li><h4>Customer.java</h4></li>
<img src="captures/Customer.png">
<li><h4>BankAccount.java</h4></li>
<img src="captures/BankAccount.png">
<li><h4>SavingAccount.java</h4></li>
<img src="captures/SavingAccount.png">
<li><h4>CurrentAccount.java</h4></li>
<img src="captures/CurrentAccount.png">
<li><h4>AccountOperation.java</h4></li>
<img src="captures/AccountOperation.png">
<li><h4>Les Enums</h4></li>
<li><h4>OperationType.java</h4></li>
<img src="captures/OperationType.png">
<li><h4>AccountStatus.java</h4></li>
<img src="captures/AccountStatus.png">
</ul>
<br/>
<h3>3. Création des interfaces JPA Repository basées sur Spring Data</h3>
<ul>
<li><h4>CustomerRepository.java</h4></li>
<img src="captures/CustomerRepository.png">
<li><h4>BankAccountRepository.java</h4></li>
<img src="captures/BankAccountRepository.png">
<li><h4>AccountOperationRepository.java</h4></li>
<img src="captures/AccountOperationRepository.png">
</ul>
<h3>4. Tester la couche DAO</h3><ul>
<li><h4>configuration de la base de données test - H2 - .application.properties</h4></li>
<img src="captures/application.properties.png">
<ul>
<li><h4>Test des entités et des repository à l'aide des @Beans et @Builder</h4></li>
<li><h4>BankingAppApplication.java</h4></li>
<img src="captures/TestDAO.png">
<li><h4>Connexion h2 database</h4></li>
<img src="captures/h2Db.png">
<img src="captures/Test-SQL.png">
</ul>
<h3>5. Couche service, DTOs</h3>
<ul>
<li><h4>Les DTOs</h4></li>
<li><h4>CustomerDTO.java</h4></li>
<img src="captures/CustomerDTO.png">
<li><h4>BankAccount.java</h4></li>
<img src="captures/BankAccountDTO.png">
</ul>







