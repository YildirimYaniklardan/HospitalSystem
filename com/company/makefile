COMPILE:=javac -target 11
PACK:=Dynamic_Graph/
FILES:=\
	$(PACK)Vertex.class \
	$(PACK)Edge.class \
	$(PACK)Graph.class \
	$(PACK)DynamicGraph.class \
	$(PACK)DGraph.class \
	Administrator.class \
	Appointment.class \
	BinarySearchTree.class \
	BinaryTree.class \
	Doctor.class \
	Employee.class \
	Equipment.class \
	Evaluation.class \
	HospitalSystem.class \
	Nurse.class \
	Patient.class \
	PatientAdmission.class \
	SearchTree.class \
	SkipList.class \
	Treatment.class \
	driver.class

compileAll:$(FILES)

%.class:%.java
	$(COMPILE) $<

run:$(FILES)
	java driver

clean:
	-rm -f $(PACK)*.class *.class
