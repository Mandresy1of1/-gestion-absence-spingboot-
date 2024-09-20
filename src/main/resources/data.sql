-- Ajouter des étudiants
INSERT INTO etudiants (nom, prenom) VALUES ('Dupont', 'Jean');
INSERT INTO etudiants (nom, prenom) VALUES ('Martin', 'Claire');

-- Ajouter des cours
INSERT INTO cours (intitule, description) VALUES ('Mathématiques', 'Cours de mathématiques avancées');
INSERT INTO cours (intitule, description) VALUES ('Physique', 'Introduction à la physique');

-- Ajouter des absences
INSERT INTO absences (etudiant_id, cours_id, date_absence, justifie) VALUES (1, 1, '2024-09-15', true);
INSERT INTO absences (etudiant_id, cours_id, date_absence, justifie) VALUES (2, 2, '2024-09-16', false);
