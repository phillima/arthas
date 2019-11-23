---
title: 'Eyestream: An Open WebSocket-based Middleware for Serializing and Streaming Eye Tracker Event Data from Gazepoint GP3 HD Research Hardware'
tags:
  - Python
  - eye tracker
  - Gazepoint 
  - websocket
  - JSON
  - streaming
authors:
  - name: Matthew L. Hale
    orcid: 0000-0002-8433-2744
    affiliation: 1
affiliations:
 - name: School of Interdisciplinary Informatics, University of Nebraska at Omaha
   index: 1
date: 21 April 2019
bibliography: paper.bib
---

# Summary

Since the introduction of code annotations in the Java language, this feature has been widely adopted by software developers across the globe. Main enterprise frameworks make extensive use of code annotations as a replacement for XML based solutions. Code annotations are inserted directly on code elements, providing a simple way to introduce custom metadata. To aid in our research that aims understanding how developers use this feature, we developed the Annotation Sniffer. An open source tool that extracts a suite of metrics dedicated to code annotations.

Source code metrics can retrieve information from software to assess its characteristics. Well-known techniques use metrics associated with rules to detect bad smells on the source code [Lanza2006,VanRompaey2007]. However, traditional code metrics does not recognize code annotations on programming elements, which can lead to an incomplete code assessment \cite{Guerra2009}. For example, a domain class can be considered simple using current complexity metrics. However, it can contain complex annotations for object-XML mapping. Also, using a set of annotations couples the application to a framework that can interpret them and current coupling metrics does not explicitly handle this.

The Java language provides code annotation as a means to introduce custom metadata on programming elements. The annotation is declared directly on the source code, as opposed to an XML approach. The latter requires an external file with the metadata, and a path to this XML file must be provided to the software consuming the metadata.

To automate the process of extracting the code annotation metrics, we developed an open source tool called Annotation Sniffier (ASniffer). It obtains the metrics values and outputs them in an XML report. The goal of this paper is to present this novel tool, since no other similar was found.

#Metadata and Object Oriented Programming

The term ``metadata'' is used in a variety of contexts in the computer science field. In all of them, it means data referring to the data itself. When discussing databases, the data are the ones persisted, and the metadata is their description, i.e., the structure of the table. In the object-oriented context, the data are the instances, and the metadata is their description, i.e., information that describes the class. As such, fields, methods, super-classes, and interfaces are all metadata of a class instance. A class field, in turn, has its type, access modifiers, and name as its metadata~\cite{guerra2014}. 

The class structure might not be enough to allow a specific behavior or routine to be executed, and therefore additional metadata can be configured on the programming elements. Afterward, a framework or tool consumes them and executes the desired behavior. For instance, metadata can be used to generate source code~\cite{Damyanov2004}, compile-time verification~\cite{Ernst2008}, framework adaptation~\cite{Guerra2010a}, perform object-relational mapping, object-XML mapping and so forth.
  
Custom metadata can be configured using external storage, such as a database or an XML file~\cite{Fernandes2010}. This approach adds verbosity to the system since it is necessary to inform a complete path between the referenced element and its metadata. Another alternative is to define code conventions~\cite{Chen}, used by the Ruby on Rails~\cite{Ruby2009} and the CakePHP framework\footnote{\url{cakephp.org}}. Developing with this method can be productive; however, it is limited when it comes to configuring more complex metadata. For this reason, some programming languages provide features that allow custom metadata to be defined and included directly on programming elements. This feature is supported in languages such as Java, through the use of annotations~\citep{JSR2004}, and in C\#, by attributes~\cite{ECMA2017}. A benefit is that the metadata definition is closer to the programming element, and its definition is less verbose than external approaches. Also, the metadata is being explicitly defined in the source code as opposed to code convention approaches. Some authors call the usage of code annotations as attribute-oriented programming since it is used to mark software elements \citep{Wada2005, Schwarz2004}. 

Annotations are a feature of the Java language, which became official on version 1.5, spreading, even more, the use of this technique in the development community. Some base APIs, starting in Java EE 5, like EJB 3.0 and JPA, use metadata in the form of annotations extensively. This native support to annotations encourages many Java frameworks and API developers to adopt the metadata-based approach in their solutions. They were also a response to the tendency of keeping the metadata files inside the source code itself, instead of using separate files \citep{CORDOBASANCHEZ2016}.

Consider the code on Figure \ref{fig:exampleClass}. It is a simple Java class representing a player from a video game code. To map this class to a table in a database, to store the player's information, we need to pass in some ``extra information'' about these code elements. In other words, we need to define an object-relational mapping, and we need to configure which elements should be mapped to a column, table, and so forth. Using code annotations provided by the JPA API, this mapping is easily achieved. When this code gets executed, the framework consuming the annotations knows how to perform the expected behavior, which occurs as described below:

Another important definition is that of an annotation schema~\cite{anom2019}, defined as a set of associated annotations that belong to the same API. The annotations used in the example code are part of the JPA schema, with the exception of \texttt{@Component}, which belongs to the Spring framework. An annotation-based API usually uses a group of related annotations that represent the set of metadata necessary for its usage.

#Annotation Metrics

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

#Annotation Sniffer 


# Citations

Citations to entries in paper.bib should be in
[rMarkdown](http://rmarkdown.rstudio.com/authoring_bibliographies_and_citations.html)
format.

For a quick reference, the following citation commands can be used:
- `@author:2001`  ->  "Author et al. (2001)"
- `[@author:2001]` -> "(Author et al., 2001)"
- `[@author1:2001; @author2:2001]` -> "(Author1 et al., 2001; Author2 et al., 2002)"

# Figures

Figures can be included like this: ![Example figure.](figure.png)

#License

Annotation Sniffer is licensed under the GNU Lesser General Public License v3.0

# Acknowledgements

This work is supported by FAPESP (Fundação de Amparo à Pequisa do Estado de São Paulo), grant 2014/16236-6 and CAPES (Coordenação de Aperfeiçoamento de Pessoal de Nível Superior )

# References
