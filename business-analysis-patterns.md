it is really detailled, all questions should be asked for each individual data.

can a data be split ?


Encodage

Clause in : non vide, pas plus de 1000

Identifiant basé sur des champs vs identifiant à part -> cas refus/annulation doivent permettre ressaisie avec les même paramètres ?

Horodatages !!! la donnée dans laquelle ont ne peut pas avoir confiance !!! heures serveur != heure java

Test réels des frameworks bizarres peu maîtrisés (batch dans des technos peu connues…)


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


Données numériques / date : null, minimum, maximum, incrément

Periode (2 dates) : faire les tests avec debut/fin != et debut/fin =

Services/orchestration -> 1 public only prevents dev that work on different features to update same file

