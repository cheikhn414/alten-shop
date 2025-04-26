
# 🚀 Rapport de Conformité – Projet E-commerce Alten

## 📋 Table des matières
- [1. Introduction](#1-introduction)
- [2. Réalisation Front-end](#2-réalisation-front-end)
  - [2.1. Fonctionnalités du Shop](#21-fonctionnalités-du-shop)
  - [2.2. Page Contact](#22-page-contact)
  - [2.3. Fonctionnalités Bonus](#23-fonctionnalités-bonus)
- [3. Réalisation Back-end](#3-réalisation-back-end)
  - [3.1. API de Gestion des Produits](#31-api-de-gestion-des-produits)
  - [3.2. Sécurisation et Gestion Utilisateurs](#32-sécurisation-et-gestion-utilisateurs)
  - [3.3. Fonctionnalités complémentaires](#33-fonctionnalités-complémentaires)
  - [3.4. Validation et Documentation](#34-validation-et-documentation)
- [4. Conclusion](#4-conclusion)

---

## 1. Introduction

Dans le cadre du projet de développement du site e-commerce d'Alten, ce document atteste que l'intégralité des consignes spécifiées pour les parties **Front-end** et **Back-end** a été rigoureusement respectée et exécutée.  
Toutes les fonctionnalités sont conformes aux attentes, avec une attention particulière portée à la qualité du code et à l'expérience utilisateur.

---

## 2. Réalisation Front-end

### 2.1. Fonctionnalités du Shop

- ✅ **Affichage** de toutes les informations pertinentes sur la liste des produits.
- ✅ **Ajout** au panier depuis la liste des produits.
- ✅ **Suppression** de produits depuis le panier.
- ✅ **Affichage dynamique** d'un badge de quantité de produits dans le panier.
- ✅ **Visualisation détaillée** du contenu du panier.

### 2.2. Page Contact

- ✅ **Ajout** d'un point de menu "Contact" dans la barre latérale.
- ✅ **Création** d'une page de **formulaire de contact** :
  - Champ **email** obligatoire.
  - Champ **message** obligatoire, limité à 300 caractères.
- ✅ **Validation** de la saisie utilisateur.
- ✅ **Affichage** d'un message de succès : _"Demande de contact envoyée avec succès"_.

### 2.3. Fonctionnalités Bonus

- ✅ **Implémentation** d'une **pagination** et d'un **tri** des prix sur la liste des produits.
- ✅ **Ajustement de la quantité** possible depuis la liste et depuis le panier.

---

## 3. Réalisation Back-end

### 3.1. API de Gestion des Produits

| Endpoint             | Méthode | Description                                   |
| -------------------- | ------- | --------------------------------------------- |
| `/products`          | POST    | Création d'un produit                        |
| `/products`          | GET     | Récupération de tous les produits            |
| `/products/{id}`     | GET     | Détails d'un produit spécifique              |
| `/products/{id}`     | PATCH   | Mise à jour partielle d'un produit            |
| `/products/{id}`     | DELETE  | Suppression d'un produit                     |

- 📦 **Respect strict** du modèle d'entité `Product` fourni.
- 🗄️ **Stockage** dans une base de données **SQL** (Base de données de type mémoire **H2** est utilisé dans ce projet).

### 3.2. Sécurisation et Gestion Utilisateurs

- 🔒 Mise en place d'un **système d'authentification JWT**.
- 🔑 **Routes** pour la création de compte (`/account`) et l'obtention d'un token (`/token`).
- 👑 **Restrictions d'accès** : Seul l'utilisateur avec l'email `admin@admin.com` peut ajouter, modifier ou supprimer des produits.

### 3.3. Fonctionnalités complémentaires

- 🛒 **Gestion du panier** pour chaque utilisateur connecté.
- 🎯 **Gestion de la liste d'envies** (wishlist) pour chaque utilisateur.

### 3.4. Validation et Documentation

- 🧪 **Tests Postman** disponibles pour toutes les routes :
  - Authentification
  - CRUD Produits
  - Panier
  - Wishlist

  Vous devez juster importer la collection ([ALTEN.postman_collection](./ALTEN.postman_collection.json)) et l'environnement ([ALTEN.postman_environment](./ALTEN.postman_environment.json)) dans postman pour reproduire les tests
---

## 4. Conclusion

L'ensemble des consignes du projet e-commerce Alten a été :
- 📚 **analysé**,
- 🛠️ **développé**,
- ✅ **testé**,
- 🚀 **validé**.

Le livrable est conforme aux attentes et prêt pour une intégration en pré-production.  
Un soin particulier a été porté à la qualité du code, à la performance de l'application et à la sécurité des données utilisateurs.

---

✨ Merci pour votre confiance !
