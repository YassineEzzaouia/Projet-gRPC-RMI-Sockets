# Application de messagerie avec gRPC

Ce projet présente une application de messagerie simple utilisant gRPC en Java. Il se compose d'un serveur qui gère l'envoi et la récupération de messages, et de clients qui peuvent interagir avec le serveur pour envoyer et recevoir des messages.

## Prérequis

- Java Development Kit (JDK) installé
- Apache Maven installé
- Compilateur de protocoles Buffer (protoc) installé
- IntelliJ IDEA (recommandé pour avoir le même environnement de développement)

## Installation

1. Clonez ce dépôt sur votre machine locale
2. Ouvrez le projet dans IntelliJ IDEA

## Utilisation

1. Compilez la définition des protocoles Buffer(MessagingService.proto) qui se trouve dans le package resources.
2. Démarrez le serveur(MessagingServer.java)
3. Démarrez le client(MessagingClient.java)
4. Suivez les invites sur la console du client pour envoyer ou récupérer des messages. Pour envoyer un message, appuyez sur 's', pour récupérer les messages, appuyez sur 'r', et pour quitter l'application, appuyez sur 'q'.

## Remarques

- Le fichier `pom.xml` contient toutes les dépendances et les plugins nécessaires pour générer les stubs gRPC.

-----------------------------------------------------------------------------------------------------------

# Service de chat avec des sockets

Ce projet démontre comment créer un service de chat simple en utilisant des sockets TCP pour la communication entre les clients et le serveur.

## Déploiement

1. Assurez-vous que vous avez Java JDK installé sur votre système.
2. Compilez les fichiers Java
3. Lancez le serveur de chat (ChatServer.java)
4. Exécutez les 2 clients que j'ai fournis(ALICE et BOB) pour se connecter au serveur (ALICE.java et BOB.java)

## Tests

1. Une fois connecté, vous pouvez envoyer des messages qui seront diffusés à tous les autres clients connectés au serveur.

## Remarques
- Vous pouvez faire autant de clients que vous souhaitez(les clients ont le meme code il faut juste changer le nom du client).

-----------------------------------------------------------------------------------------------------------

# Gestion d'une liste de tâches avec Java RMI

Ce projet démontre comment créer un service de gestion de liste de tâches en utilisant Java RMI.

## Déploiement

1. Assurez-vous que vous avez Java JDK installé sur votre système.
2. Compilez les fichiers Java.
3. Lancez le registre RMI en exécutant le serveur RMI 
4. Exécutez le client RMI pour interagir avec le service 

## Tests

1. Une fois que le client est lancé, vous pouvez ajouter, supprimer et récupérer des tâches à partir du service de liste de tâches.


