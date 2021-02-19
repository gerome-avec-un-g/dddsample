#Generic
* it is really detailled, all questions should be asked for each individual data.
* Can the concept be split ? (type of overpaid hours which contained both if it is paid/recovered and some business name tied to time percentage)
* It is raw data (state) or can it be computed (status) ? Error : needing a batch to update raw data to reflect the fact that now if after some date
(batch to update state to closed when now if after closing date instead of just computing it)

#Identifier
* Avoid database sequence. Errors : identifier becomes optional (increased complexity) in your domain because at creation in domain you don't have the sequence value and JPA requires some complex post processing. Your domain objects should be simple and valid at any time (and don't cheat by creating a CreationRequest object that is your domain object without identifier.... 
* Is it composed of some fields or is it randomized ? Can I cancel (still exists in database with a specific state) and recreate with the same values ? Or can I only delete (database )
* Don't use random identifiers for business concepts. Error : business people saying 65042 instead of "France" (mental mapping)
* Use enum like String identifier with full business semantics
* Use UUID generated from a repository for ease of testing
* Wrap the String/UUID in a class with a semantic name : XXXIdentifier. Not all identifiers are the same! Compilation will detect when passing the wrong kind of identifier

#Timestamps
* Dates and times are hard, so be very careful
* You may have different systems generating timestamps, you can't really trust they are all set correctly. Error: JVM timestamps where given in UTC while server/scripts timestamps where in local time so there was a 1 hour difference in the data saved
* If you need to maintain order, don't rely on timestamps, use a "Step" enum with order

#Dates

#Date range
Periode (2 dates) : faire les tests avec debut/fin != et debut/fin =

#Strings

#Numbers
Données numériques / date : null, minimum, maximum, incrément




#TODO
Encodage

Clause in : non vide, pas plus de 1000



Tests réels des frameworks bizarres peu maîtrisés (batch dans des technos peu connues…)


Egalement toujours bien séparer les frameworks techniques et les foncions métier ! pour rendre moins dépendant de la techno non maîtrisée !


workflow » d’une fonctionnalité/feature :

Vérification des données en entrées (« format ») : la donnée est valable dans l’absolu

Vérification des données entrées (autorisations, périmètre, ) : les données est valable dans le relatifs, c’est-à-dire compatible avec l’état actuel du système

Cas passants et leur conséquences


Workflow d’une fonctionalité « web »



login

menus

afficher l’écran de saisie du formulaire (note : le formulaire peut être des cases à cocher sur un écran récapitulatif)

envoyer le formulaire

afficher le résultat de l’action

Erreur classiques sur les chaînes de caractères : la casse



Services/orchestration -> 1 public only prevents dev that work on different features to update same file

