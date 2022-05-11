#Generic
* it is really detailed, all questions should be asked for each individual data.
* Can the concept be split ? (type of overpaid hours which contained both if it is paid/recovered and some business name tied to time percentage)
* It is raw data (state) or can it be computed (status) ? Error : needing a batch to update raw data to reflect the fact that now if after some date
(batch to update state to closed when now if after closing date instead of just computing it)

#Identifier
* Avoid database sequence. Errors : identifier becomes optional (increased complexity) in your domain because at creation in domain you don't have the sequence value and JPA requires some complex post-processing. Your domain objects should be simple and valid at any time (and don't cheat by creating a CreationRequest object that is your domain object without identifier....) 
* Is it composed of some fields or is it randomized ? Can I cancel (still exists in database with a specific state) and recreate with the same values ? Or can I only delete (database )
* Don't use random identifiers for business concepts. Error : business people saying 65042 instead of "France" (mental mapping)
* Use enum like String identifier with full business semantics
* Use UUID generated from a repository for ease of testing
* Wrap the String/UUID in a class with a semantic name : XXXIdentifier. Not all identifiers are the same! Compilation will detect when passing the wrong kind of identifier

#Timestamps
* Dates and times are hard, so be very careful
* You may have different systems generating timestamps, you can't really trust they are all set correctly. Error: JVM timestamps where given in UTC while server/scripts timestamps where in local time so there was a one-hour difference in the data saved
* If you need to maintain order, don't rely on timestamps, use a "Step" enum with order

#Dates

#Date range
Période (2 dates) : faire les tests avec debut/fin != et debut/fin =

#Strings

#Numbers
Données numériques / date : null, minimum, maximum, incrément




#TODO
Encodage

Clause in : non vide, pas plus de 1000



Tests réels des frameworks bizarres peu maîtrisés (batch dans des technos peu connues…)


Egalement toujours bien séparer les frameworks techniques et les foncions métier ! Pour rendre moins dépendant de la techno non maîtrisée !


workflow » d’une fonctionnalité/feature :

Vérification des données saisies (format, valeurs...) : les données sont valables dans l’absolu, voire, entre-elles -> règles dans l'objet contenant les champs ou dans l'agrégat.

Vérification des données par rapport au "bounded context" (existence en base de données, doublons... ) : les données sont compatibles avec l’état actuel du système -> dans un service

Vérification des données par rapport à d'autres "bounded context" (autorisations, périmètre... ) : les données sont compatibles avec l’état actuel du système -> dans un service d'orchestration ? Sauf si le contrôle peut être réduit à un modèle trivial auquel cas on peut repasser la donnée au domaine initial ?

Cas passants et leurs conséquences


Workflow d’une fonctionnalité « web »



login

menus

Afficher l’écran de saisie du formulaire (note : le formulaire peut être des cases à cocher sur un écran récapitulatif)

Envoyer le formulaire

Afficher le résultat de l’action

Erreur classique sur les chaînes de caractères : la casse



Services/orchestration -> 1 public only prevents dev that work on different features to update same file

