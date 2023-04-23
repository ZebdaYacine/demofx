use doctorlite;
ALTER TABLE  diagnostic
ADD idDoctor bigint,
ADD idPsychologist bigint,
ADD FOREIGN KEY (idDoctor) REFERENCES user(id),
ADD FOREIGN KEY (idPsychologist) REFERENCES user(id);
SELECT * FROM doctorlite.diagnostic;