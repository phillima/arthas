---
title: 'Annotation Sniffer: An Open Source Tool for Code Annotation Metrics'
tags:
  - Java
  - metadata
  - code annotations 
  - software-enginering
authors:
  -name: Phyllipe Lima
        orcid: 0000-0002-8358-4405
        affiliation: 1
affiliations:
    - name: CDG, National Institute of Telecommunications - INATEL, Brazil
        index: 1
date: 23 November 2019
bibliography: paper.bib
---

# Summary

Since the introduction of code annotations in the Java language, this feature has been widely adopted by software developers across the globe. Main enterprise frameworks make extensive use of code annotations as a replacement for XML based solutions. Code annotations are inserted directly on code elements, providing a simple way to introduce custom metadata. To aid in our research that aims understanding how developers use this feature, we developed the Annotation Sniffer. An open source tool that extracts a suite of metrics dedicated to code annotations.

Source code metrics can retrieve information from software to assess its characteristics. Well-known techniques use metrics associated with rules to detect bad smells on the source code [@Lanza2006],[@VanRompaey2007]. However, traditional code metrics does not recognize code annotations on programming elements, which can lead to an incomplete code assessment [@Guerra2009]. For example, a domain class can be considered simple using current complexity metrics. However, it can contain complex annotations for object-XML mapping. Also, using a set of annotations couples the application to a framework that can interpret them and current coupling metrics does not explicitly handle this.

The Java language provides code annotation as a means to introduce custom metadata on programming elements. The annotation is declared directly on the source code, as opposed to an XML approach. The latter requires an external file with the metadata, and a path to this XML file must be provided to the software consuming the metadata.

To automate the process of extracting the code annotation metrics, we developed an open source tool called Annotation Sniffier (ASniffer). It obtains the metrics values and outputs them in an XML report. The goal of this paper is to present this novel tool, since no other similar was found.

# Metadata and Object Oriented Programming

The term `metadata` is used in a variety of contexts in the computer science field. In all of them, it means data referring to the data itself. When discussing databases, the data are the ones persisted, and the metadata is their description, i.e., the structure of the table. In the object-oriented context, the data are the instances, and the metadata is their description, i.e., information that describes the class. As such, fields, methods, super-classes, and interfaces are all metadata of a class instance. A class field, in turn, has its type, access modifiers, and name as its metadata [@guerra2014]

# Annotation Metrics

```java
import javax.persistence.AssociationOverrides;
import javax.persistence.AssociationOverride;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.DiscriminatorColumn;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

@AssociationOverrides(value = {
      @AssociationOverride(name="ex",
         joinColumns = @JoinColumn(name="EX_ID")),
      @AssociationOverride(name="other",
         joinColumns = @JoinColumn(name="O_ID"))})
@NamedQuery(name="findByName",
      query="SELECT c " +
            "FROM Country c " + 
            "WHERE c.name = :name")
@Stateless
public class Example {...

   @TransactionAttribute(SUPPORTS)
   @DiscriminatorColumn(name = "type", discriminatorType = STRING)
   public String exampleMethodA(){...}

   @TransactionAttribute(SUPPORTS)
   public String exampleMethodB(){...}

   @TransactionAttribute(SUPPORTS)
   public String exampleMethodC(){...}
}

```

# Annotation Sniffer 

# License

Annotation Sniffer is licensed under the GNU Lesser General Public License v3.0

# Acknowledgements

This work is supported by FAPESP (Fundação de Amparo à Pequisa do Estado de São Paulo), grant 2014/16236-6 and CAPES (Coordenação de Aperfeiçoamento de Pessoal de Nível Superior )

# References
